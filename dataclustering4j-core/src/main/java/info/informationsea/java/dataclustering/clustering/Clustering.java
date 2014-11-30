/**
 *  dataclustering4j
 *  Copyright (C) 2014 Yasunobu OKAMURA
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package info.informationsea.java.dataclustering.clustering;

import info.informationsea.java.dataclustering.distance.DistanceMatrixMaker;
import info.informationsea.java.dataclustering.linkage.Linkage;

import java.util.ArrayList;

/**
 * Main class of dataclustering4j
 *
 */
public class Clustering {

    private Clustering(){}

    /**
     * Do hierarchical clustering
     *
     * @param distanceMatrix A matrix of distances between items
     * @param linkage Linkage method
     * @return The result of hierarchical clustering
     */
    public static ClusterNode doClustering(DistanceMatrixMaker.DistanceMatrix distanceMatrix, Linkage linkage) {
        ArrayList<ClusterNode> currentNodes = new ArrayList<ClusterNode>();
        int itemSize = distanceMatrix.getSize()[0];

        for (int i = 0; i < itemSize; ++i) {
            currentNodes.add(new ClusterLeaf(i));
        }

        while (currentNodes.size() > 1) {
            int currentNodeNum = currentNodes.size();
            double mindistance = linkage.distance(distanceMatrix, currentNodes.get(0), currentNodes.get(1));
            int[] mindistanceIndexes = new int[]{0, 1};

            for (int i = 0; i < currentNodeNum; ++i) {
                for (int j = i+1; j < currentNodeNum; ++j) {
                    double distance = linkage.distance(distanceMatrix, currentNodes.get(i), currentNodes.get(j));
                    if (distance < mindistance) {
                        mindistanceIndexes[0] = i;
                        mindistanceIndexes[1] = j;
                        mindistance = distance;
                    }
                }
            }

            currentNodes.add(new ClusterBranch(currentNodes.get(mindistanceIndexes[0]),
                    currentNodes.get(mindistanceIndexes[1])));
            currentNodes.remove(mindistanceIndexes[1]);
            currentNodes.remove(mindistanceIndexes[0]);
        }

        return currentNodes.get(0);
    }
}
