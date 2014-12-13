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

package info.informationsea.dataclustering4j.test.clustering.impl;

import info.informationsea.dataclustering4j.clustering.impl.ClusterBranch;
import info.informationsea.dataclustering4j.clustering.impl.ClusterLeaf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClusterNodeTest {

    private ClusterLeaf leaf1;
    private ClusterLeaf leaf2;
    private ClusterLeaf leaf3;
    private ClusterBranch branch1;
    private ClusterBranch branch2;

    @Before
    public void setUp() {
        leaf1 = new ClusterLeaf(1);
        leaf2 = new ClusterLeaf(10);
        leaf3 = new ClusterLeaf(7);
        branch1 = new ClusterBranch(leaf1, leaf2, 1);
        branch2 = new ClusterBranch(branch1, leaf3, 1);
    }

    @Test
    public void testClusterLeafToString() {
        Assert.assertEquals("Node[1]", leaf1.toString());
        Assert.assertEquals("Node[10]", leaf2.toString());
        Assert.assertEquals("Node[7]", leaf3.toString());
    }

    @Test
    public void testClusterLeafGetChildren() {
        Assert.assertArrayEquals(new ClusterLeaf[]{}, leaf1.getChildren());
        Assert.assertArrayEquals(new ClusterLeaf[]{}, leaf2.getChildren());
        Assert.assertArrayEquals(new ClusterLeaf[]{}, leaf3.getChildren());
    }

    @Test
    public void testClusterLeafEqual() {
        Assert.assertEquals(new ClusterLeaf(1), leaf1);
        Assert.assertEquals(new ClusterLeaf(10), leaf2);
        Assert.assertEquals(new ClusterLeaf(7), leaf3);
        Assert.assertNotEquals(new ClusterLeaf(8), leaf3);
        Assert.assertNotEquals(leaf2, leaf3);
        Assert.assertFalse(leaf3.equals(branch1));
    }

    @Test
    public void testClusterBranchToString() {
        Assert.assertEquals("Branch(1.00e+00)[\nNode[1]\nNode[10]]", branch1.toString());
        Assert.assertEquals("Branch(1.00e+00)[\nNode[7]\nBranch(1.00e+00)[\nNode[1]\nNode[10]]]", branch2.toString());
    }

    @Test
    public void testClusterBranchEqual() {
        Assert.assertTrue(branch1.equals(new ClusterBranch(new ClusterLeaf(1), new ClusterLeaf(10), 1)));
        Assert.assertFalse(branch1.equals(new ClusterBranch(new ClusterLeaf(1), new ClusterLeaf(10), 2)));
        Assert.assertFalse(branch1.equals(new ClusterBranch(new ClusterLeaf(1), new ClusterLeaf(7), 1)));
        Assert.assertFalse(branch1.equals(new ClusterBranch(new ClusterLeaf(9), new ClusterLeaf(10), 1)));
        Assert.assertFalse(branch2.equals(new ClusterBranch(new ClusterLeaf(1), new ClusterLeaf(10), 1)));
        Assert.assertFalse(branch1.equals(leaf1));
    }

    @Test
    public void testIsLeaf() {
        Assert.assertTrue(leaf1.isLeaf());
        Assert.assertFalse(branch1.isLeaf());
    }
}
