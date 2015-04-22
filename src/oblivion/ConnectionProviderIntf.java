/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import java.awt.Point;

/**
 *
 * @author kevin.lawrence
 */
public interface ConnectionProviderIntf {
//    public Point getConnection(Block block);
    public Point getLocation();
    public int getWidth();
    public int getHeight();
}
