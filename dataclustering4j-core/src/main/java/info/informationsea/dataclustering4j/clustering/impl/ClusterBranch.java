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

package info.informationsea.dataclustering4j.clustering.impl;

import info.informationsea.dataclustering4j.clustering.ClusterNode;

import java.util.HashSet;
import java.util.Set;

public class ClusterBranch implements ClusterNode {

    ClusterNode m_left;
    ClusterNode m_right;
    double m_distance;

    Set<Integer> m_leafIndexes;

    public ClusterBranch(ClusterNode left, ClusterNode right, double distance) {
        m_left = left;
        m_right = right;
        m_distance = distance;

        m_leafIndexes = new HashSet<Integer>();
        m_leafIndexes.addAll(m_left.leafIndexes());
        m_leafIndexes.addAll(m_right.leafIndexes());
    }

    @Override
    public String toString() {
        return String.format("Branch(%.2e)[\n%s\n%s]", m_distance, m_left.toString(), m_right.toString());
    }

    @Override
    public Set<Integer> leafIndexes() {
        return m_leafIndexes;
    }

    @Override
    public ClusterNode[] getChildren() {
        if ((Integer) m_left.leafIndexes().toArray()[0] <
                (Integer) m_right.leafIndexes().toArray()[0]) {
            return new ClusterNode[]{m_left, m_right};
        } else {
            return new ClusterNode[]{m_right, m_left};
        }
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public double distance() {
        return m_distance;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ClusterBranch) {
            ClusterBranch cb = (ClusterBranch)obj;

            ClusterNode[] a = cb.getChildren();
            ClusterNode[] b = getChildren();
            return a[0].equals(b[0]) && a[1].equals(b[1]) && cb.distance() == distance();
        }
        return super.equals(obj);
    }
}
