/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import java.awt.Rectangle;

/**
 *
 * @author kevin.lawrence
 */
public interface ConnectionUpdateHandlerIntf {
    public void onUpdate(Rectangle connector, Rectangle connected);
}
