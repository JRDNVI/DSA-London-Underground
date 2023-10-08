package com.example.assign2_dsa2_dbp_jc;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.assign2_dsa2_dbp_jc.Dijkstra.findCheapestPathDijkstra;

public class MainController {


    public ImageView zone1Image;

    public Hashtable<Integer, GraphNode<Station>> stationsById = new Hashtable<>();
    public Hashtable<String, GraphNode<Station>> stationsByName = new Hashtable<>();
    public Hashtable<Integer, List<GraphLink>> connectionsByLineNumber = new Hashtable<>();
    public Hashtable<String, List<Zone1Station>> zone1StationsByName = new Hashtable<>();


    public AnchorPane drawingPane;
    public Group circleGroup = new Group();

    public ChoiceBox<String> traverseMode;
    public ChoiceBox<String> destNodeCB;
    public ChoiceBox<String> startNodeCB;
    public ChoiceBox<String> waypointCB;
    public ChoiceBox<String> stationToAvoidCB;

    public ListView<String> waypointsListView;
    public ListView<String> avoidListView;
    public ListView<String> pathList;

    public Button addWaypoints;
    public Button addAvoidNodes;
    public Button clearInfo;

    public void initialize() throws Exception {
        List<Station> stations = Parser.parseStations();
        List<LineDefinition> connections = Parser.parseLineDefinitions();
        List<Zone1Station> zone1Stations = Parser.parseZone1Stations();
        buildZone1StationHashtableByName(zone1Stations);
        buildStationHashtableByIdAndName(stations);
        buildLineDefinitionsIntoGraph(connections);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/assign2_dsa2_dbp_jc/Image/LondonUndergroundZone1BNW.png")));
        zone1Image.setImage(image);
        traverseMode.setValue("Dijkstra's");
        traverseMode.getItems().addAll("Breadth First Search", "Dijkstra's", "Depth First Search");
        drawingPane.setOnMousePressed(this::getMousePos);
        drawCirclesOnZone1Stations();
        fillChoiceBoxes(stations);
    }

    public void fillChoiceBoxes(List<Station> stations) {
        for (Station station : stations) {
            String nameAndZone = station.getName();
            startNodeCB.getItems().add(nameAndZone);
            destNodeCB.getItems().add(nameAndZone);
            waypointCB.getItems().add(nameAndZone);
            stationToAvoidCB.getItems().add(nameAndZone);
        }
    }

    public void buildStationHashtableByIdAndName(List<Station> stations) {
        for (Station station : stations) {
            int stationId = station.getId();
            String name = station.getName();

            GraphNode<Station> stationGraphNode = new GraphNode<>(station);
            stationsById.put(stationId, stationGraphNode);
            stationsByName.put(name, stationGraphNode);
        }
    }

    public void buildZone1StationHashtableByName(List<Zone1Station> stations) {
        for (Zone1Station station : stations) {
            String name = station.getStationName();
            if (zone1StationsByName.get(name) != null) zone1StationsByName.get(name).add(station);
            else {
                zone1StationsByName.put(name, new ArrayList<>());
                zone1StationsByName.get(name).add(station);
            }
        }
    }

    public void buildLineDefinitionsIntoGraph(List<LineDefinition> lineDefinitions) {
        for (LineDefinition connection : lineDefinitions) {
            int expectedStation1 = connection.getStationOneID();
            int expectedStation2 = connection.getStationTwoID();
            int onLine = connection.getLine();

            GraphNode<Station> station1 = stationsById.get(expectedStation1);
            GraphNode<Station> station2 = stationsById.get(expectedStation2);

            int cost = (int) Utilities.distance(station1.data, station2.data);

            GraphLink stationOneToTwo = new GraphLink(station2, cost, onLine);
            GraphLink stationTwoToOne = new GraphLink(station1, cost, onLine);

            station1.connectToNodeUndirected(station2, stationOneToTwo);

            if (!connectionsByLineNumber.containsKey(onLine)) {
                connectionsByLineNumber.put(onLine, new ArrayList<>(Arrays.asList(stationOneToTwo, stationTwoToOne)));
            } else {
                connectionsByLineNumber.get(onLine).addAll(Arrays.asList(stationOneToTwo, stationTwoToOne));
            }
        }
    }

    public void printStationsById() {
        Enumeration<Integer> keys = stationsById.keys();
        while (keys.hasMoreElements()) {
            int key = keys.nextElement();
            GraphNode<Station> station = stationsById.get(key);
            List<GraphLink> adjList = station.adjList;
            System.out.println("Station " + station.data.getId() + " adjacency list :");
            for (GraphLink graphLink : adjList) {
                GraphNode<Station> destinationNode = (GraphNode<Station>) graphLink.destNode;
                System.out.println("\n" + destinationNode.data + " Cost = " + graphLink.cost + " | On line " + graphLink.line);
            }
        }
    }

    public void traverseUsingWaypoints() {
        pathList.getItems().clear();
        drawingPane.getChildren().clear();
        drawingPane.getChildren().add(circleGroup);
        CostedPath fullList = new CostedPath();
        List<GraphNode<Station>> waypointsAsNodes = new ArrayList<>();
        List<GraphNode<Station>> stationToAvoid = new ArrayList<>();

        GraphNode<Station> startNode = stationsByName.get(startNodeCB.getValue());
        waypointsAsNodes.add(startNode);

        for (int i = 0; i < waypointsListView.getItems().size(); i++) {
            GraphNode<Station> station = stationsByName.get(waypointsListView.getItems().get(i));
            waypointsAsNodes.add(station);
        }

        for (int i = 0; i < avoidListView.getItems().size(); i++) {
            GraphNode<Station> station = stationsByName.get(avoidListView.getItems().get(i));
            stationToAvoid.add(station);
        }

        GraphNode<Station> destNode = stationsByName.get(destNodeCB.getValue());
        waypointsAsNodes.add(destNode);

        switch (traverseMode.getValue()) {
            case "Depth First Search" -> {
                Random random = new Random();
                for (int y = 1; y <= 3; y++) {
                    if (y > 1) {
                        int randomIndex = random.nextInt(fullList.pathList.size() - 2) + 1;
                        GraphNode<?> node = fullList.pathList.get(randomIndex);
                        System.out.println(node);
                        stationToAvoid.add((GraphNode<Station>) node);
                    }
                    for (int i = 0; i < waypointsAsNodes.size() - 1; i++) {
                        if (waypointsAsNodes.get(i + 1) != null) {
                            CostedPath cpa = CostedPath.searchGraphDepthFirstCosted(waypointsAsNodes.get(i), waypointsAsNodes.get(i + 1).data, stationToAvoid);
                            if (i != 0) {
                                fullList.pathList.remove(fullList.pathList.size() - 1);
                            }
                            fullList.pathList.addAll(cpa.pathList);
                            pathList.getItems().addAll("Total Cost:" + cpa.pathCost);
                            fullList.pathCost += cpa.pathCost;
                        }
                    }
                }
            }
            case "Breadth First Search" -> {
                for (int i = 0; i < waypointsAsNodes.size() - 1; i++) {
                    if (waypointsAsNodes.get(i + 1) != null) {
                        CostedPath cpa = BreadthFirstSearch.searchGraphBreadthFirstCheapestPath(waypointsAsNodes.get(i), waypointsAsNodes.get(i + 1).data, stationToAvoid);
                        if (i != 0) fullList.pathList.remove(fullList.pathList.size() - 1);
                        fullList.pathList.addAll(cpa.pathList);
                        fullList.pathCost += cpa.pathCost;
                    }
                }
            }
            case "Dijkstra's" -> {
                for (int i = 0; i < waypointsAsNodes.size() - 1; i++) {
                    if (waypointsAsNodes.get(i + 1) != null) {
                        CostedPath cpa = findCheapestPathDijkstra(waypointsAsNodes.get(i), stationToAvoid, waypointsAsNodes.get(i + 1).data);
                        if (i != 0) fullList.pathList.remove(fullList.pathList.size() - 1);
                        fullList.pathList.addAll(cpa.pathList);
                        fullList.pathCost += cpa.pathCost;
                    }
                }
            }
        }

        for (int i = 0; i < fullList.pathList.size(); i++) {
            Station station = (Station) fullList.pathList.get(i).data;
            pathList.getItems().addAll(station.listViewString());

            if (zone1StationsByName.get(station.getName()) != null) {
                if (i + 1 < fullList.pathList.size()) {
                    Station nextStation = (Station) fullList.pathList.get(i + 1).data;
                    if (zone1StationsByName.get(nextStation.getName()) != null) {
                        Zone1Station station1 = zone1StationsByName.get(station.getName()).get(0);
                        Zone1Station station2 = zone1StationsByName.get(nextStation.getName()).get(0);
                        Line line = new Line(station1.getxCoord(), station1.getyCoord(), station2.getxCoord(), station2.getyCoord());
                        line.setStroke(Color.BLUE);
                        line.setStrokeWidth(2.0);
                        drawingPane.getChildren().add(line);
                    }
                }
            }
        }
        pathList.getItems().addAll("Path Cost: " + fullList.pathCost);
    }

    public void drawCirclesOnZone1Stations() {
        Enumeration<String> keys = zone1StationsByName.keys();
        int xCoord;
        int yCoord;
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            List<Zone1Station> stations = zone1StationsByName.get(key);

            for (Zone1Station station : stations) {
                xCoord = station.getxCoord();
                yCoord = station.getyCoord();

                Circle circle = drawCircle(xCoord, yCoord);
                circle.setOnMousePressed(mouseEvent -> {
                    System.out.println(station);
                });
                circleGroup.getChildren().add(circle);
            }
        }
        drawingPane.getChildren().add(circleGroup);
    }

    public Circle drawCircle(int centreX, int centreY) {
        Circle circle = new Circle();
        circle.setCenterX(centreX);
        circle.setCenterY(centreY);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.TRANSPARENT);
        circle.setStrokeWidth(2.0);
        circle.setRadius(8);
        return circle;
    }

    public void getMousePos(MouseEvent mouseEvent) {
        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();

        System.out.println("\nMouseX: " + mouseX + " || MouseY: " + mouseY);
    }

    public void populateWaypointListView() {
        String waypoint = waypointCB.getValue();
        waypointsListView.getItems().add(waypoint);
    }

    public void populateAvoidListView() {
        String nodeToAvoid = stationToAvoidCB.getValue();
        avoidListView.getItems().add(nodeToAvoid);
    }

    public void clearListviews() {
        avoidListView.getItems().clear();
        waypointsListView.getItems().clear();
    }
}