package com.company;

import java.util.*;

public class Algorithm {

    private List<ObjectDefinition> objectDefinitions;
    private List<ObjectDefinition> nu;

    public Algorithm(int windowWidth, int windowHeight, int vectorQuantity, int classesQuantity) {
        objectDefinitions = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < vectorQuantity; i++) {
            objectDefinitions.add(new ObjectDefinition(random.nextInt(0, windowWidth),
                    random.nextInt(0, windowHeight)));
        }

        Set<ObjectDefinition> centerVectors = new HashSet<>(0);
        while (centerVectors.size() < classesQuantity) {
            centerVectors.add(objectDefinitions.get(random.nextInt(0, vectorQuantity)));
        }

        nu = new ArrayList<>(centerVectors);

        for (int i = 0; i < nu.size(); i++) {
            nu.get(i).setClas(i);
            nu.get(i).setCenter(true);
        }
    }

    public List<ObjectDefinition> iteration() {

        objectDefinitions.forEach((objectDefinition -> {
            double min = vectorDistance(nu.get(0), objectDefinition);
            double curr;
            int ind = 0;
            if (!nu.contains(objectDefinition)) {
                for (int i = 0; i < nu.size(); i++) {
                    curr = vectorDistance(nu.get(i), objectDefinition);
                    if (curr < min) {
                        ind = i;
                        min = curr;
                    }
                }
                objectDefinition.setClas(ind);

            }
        }));
        objectDefinitions.sort((Comparator.comparingInt(ObjectDefinition::getClas)));
        return objectDefinitions;
    }

    private static double vectorDistance(ObjectDefinition vCenter, ObjectDefinition v2) {
        return Math.sqrt(Math.pow(Math.abs(vCenter.getX() - v2.getX()), 2) + Math.pow(Math.abs(vCenter.getY() - v2.getY()), 2));
    }

    public boolean resetCenters() {

        int classNum = 0;
        boolean isRight = true;
        int startInd = 0;

        for (int k = 0; k < nu.size(); k++) {
            int finalK = k;

            List<ObjectDefinition> inCurrClass = objectDefinitions.stream().filter(od -> od.getClas() == finalK).toList();
            double min = Double.MAX_VALUE;
            int ind = 0;
            for (int i = 0; i < inCurrClass.size(); i++) {
                double avg = 0;

                for (int j = 0; j < inCurrClass.size(); j++) {
                    avg += vectorDistance(inCurrClass.get(i), inCurrClass.get(j)) * vectorDistance(inCurrClass.get(i), inCurrClass.get(j));
                }
                if (avg < min) {
                    min = avg;
                    ind = i;
                }

            }
            if (inCurrClass.get(ind) != nu.get(k)) {
                inCurrClass.get(ind).setCenter(true);
                nu.get(k).setCenter(false);
                nu.set(k, inCurrClass.get(ind));
                isRight = false;
            }
        }

        return isRight;
    }
}
