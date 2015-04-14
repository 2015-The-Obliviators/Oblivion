/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

import environment.ApplicationStarter;

/**
 *
 * @author kevin.lawrence
 */
public class PlatformPhysics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationStarter.run("Platform Physics Test", new PlatformEnvironment());
    }
    
}
