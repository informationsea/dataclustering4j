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

package info.informationsea.dataclustering4j.clustering;

import java.util.HashSet;
import java.util.Set;

public class ClusterLeaf implements ClusterNode {

    Set<Integer> m_leaf;

    public ClusterLeaf(int leafIndex) {
        m_leaf = new HashSet<Integer>();
        m_leaf.add(leafIndex);
    }

    @Override
    public Set<Integer> leafIndexes() {
        return m_leaf;
    }

    @Override
    public ClusterNode[] getChildren() {
        return new ClusterNode[0];
    }

    @Override
    public String toString() {
        return String.format("Node[%d]", ((Number)m_leaf.toArray()[0]).intValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ClusterLeaf) {
            return ((ClusterLeaf)obj).leafIndexes().toArray()[0].equals(m_leaf.toArray()[0]);
        }
        return super.equals(obj);
    }
}
