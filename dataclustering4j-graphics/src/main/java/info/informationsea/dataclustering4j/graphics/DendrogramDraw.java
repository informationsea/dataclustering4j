package info.informationsea.dataclustering4j.graphics;

import info.informationsea.dataclustering4j.clustering.ClusterNode;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * dataclustering4j
 * Copyright (C) 2014 OKAMURA Yasunobu
 * Created on 14/12/11.
 */
public class DendrogramDraw {

    public final static int DEFAULT_SIZE = 400;

    private int m_width = DEFAULT_SIZE;
    private int m_height = DEFAULT_SIZE;
    private boolean m_fillBackground = false;
    private Graphics2D m_graphics;
    private String m_title = null;
    private Font m_font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

    public DendrogramDraw(Graphics2D graphics) {
        m_graphics = graphics;
    }

    public DendrogramDraw(BufferedImage image) {
        m_graphics = (Graphics2D) image.getGraphics();
        m_width = image.getWidth();
        m_height = image.getHeight();
    }

    public DendrogramDraw setSize(int width, int height) {
        m_width = width;
        m_height = height;
        return this;
    }

    public DendrogramDraw setTitle(String title) {
        m_title = title;
        return this;
    }

    public DendrogramDraw setFillBackground(boolean fillBackground) {
        m_fillBackground = fillBackground;
        return this;
    }

    public DendrogramDraw setFont(Font font) {
        m_font = font;
        return this;
    }

    private void drawStringAtCenter(String text, float x, float y) {
        Rectangle2D textRect = m_font.getStringBounds(text, new FontRenderContext(new AffineTransform(), true, true));
        m_graphics.drawString(text, (float)(x - textRect.getWidth()/2), (float)(y + textRect.getHeight()/2));
    }

    private static final float MARGIN_TITLE = 40;
    private static final float MARGIN = 20;
    private static final float MARGIN_BOTTOM = 50;

    public void draw(ClusterNode node) {
        m_graphics.setColor(Color.BLACK);
        m_graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        float topMargin = MARGIN;
        if (m_title != null) {
            topMargin = MARGIN_TITLE;
            drawStringAtCenter(m_title, m_width / 2, MARGIN_TITLE / 2);
        }

        Rectangle2D drawArea = new Rectangle2D.Float(MARGIN, topMargin, m_width - MARGIN*2, m_height - topMargin - MARGIN_BOTTOM);

        // calculate maximum distance
        double maximumDistance = getMaximumDistance(node);
        double distanceHeightFactor = drawArea.getHeight()/maximumDistance;
        drawNode(node, drawArea, distanceHeightFactor);
    }

    private double getMaximumDistance(ClusterNode node) {
        double distance = 0;
        for (ClusterNode child : node.getChildren()) {
            double childDistance = getMaximumDistance(child);
            if (childDistance > distance)
                distance = childDistance;
        }
        return distance + node.distance();
    }

    private void drawNode(ClusterNode node, Rectangle2D drawArea, double distanceHeightFactor) {
        if (node.isLeaf()) {
            drawStringAtCenter(node.leafIndexes().toArray()[0].toString(), (float)drawArea.getCenterX(), (float)drawArea.getY()+8);
        } else {
            float height = (float) (distanceHeightFactor * node.distance());
            Rectangle2D areaForThisNode = new Rectangle2D.Double(drawArea.getX(), drawArea.getY(), drawArea.getWidth(), height);
            double sumOfWidth = 0;
            double newY = drawArea.getY() + areaForThisNode.getHeight();
            double[] Xpositions = new double[2];
            int i = 0;

            for (ClusterNode child : node.getChildren()) {
                double weight = child.leafIndexes().size() / (double) node.leafIndexes().size();
                double newWidth = weight * drawArea.getWidth();
                Rectangle2D newDrawArea = new Rectangle2D.Double(sumOfWidth + drawArea.getX(), newY, newWidth, drawArea.getHeight() - areaForThisNode.getHeight());

                double Xpos = newDrawArea.getWidth() / 2 + newDrawArea.getX();
                Xpositions[i] = Xpos;
                Line2D line = new Line2D.Double(Xpos, areaForThisNode.getY(), Xpos, newDrawArea.getY());
                m_graphics.draw(line);

                drawNode(child, newDrawArea, distanceHeightFactor);
                sumOfWidth += newWidth;
                i++;
            }
            m_graphics.draw(new Line2D.Double(Xpositions[0], drawArea.getY(), Xpositions[1], drawArea.getY()));
        }
    }
}
