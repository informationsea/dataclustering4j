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

package info.informationsea.java.dataclustering.linkage;

import info.informationsea.java.dataclustering.clustering.ClusterNode;
import info.informationsea.java.dataclustering.distance.DistanceMatrixMaker;

public class CompleteLinkage implements Linkage {
    @Override
    public double distance(DistanceMatrixMaker.DistanceMatrix distanceMatrix, ClusterNode node1, ClusterNode node2) {
        double maxvalue = 0;

        for(Integer i: node1.leafIndexes()) {
            for (Integer j:node2.leafIndexes()) {
                maxvalue = Math.max(distanceMatrix.at(i, j), maxvalue);
            }
        }
        return maxvalue;
    }
}
