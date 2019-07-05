/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 * 
 * This software is experimental. NIST assumes no responsibility whatsoever 
 * for its use by other parties, and makes no guarantees, expressed or 
 * implied, about its quality, reliability, or any other characteristic. 
 * We would appreciate acknowledgement if the software is used. 
 * This software can be redistributed and/or modified freely provided 
 * that any derivative works bear some notice that they are derived from it, 
 * and any modified versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package crcl.ui.client;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class PendantClientJPanelTest {
    
    private final CrclSwingClientJPanel pendantClientJPanel = new CrclSwingClientJPanel();
    
    public PendantClientJPanelTest() {
    }
    
    @Before
    public void setUp() throws IOException {
        pendantClientJPanel.setPropertiesFile(File.createTempFile("pendantClientJPanelProperties", ".txt"));
    }

    @Test
    public void testSaveProperties() {
        int p1 = 99999;
        pendantClientJPanel.setPort(p1);
        pendantClientJPanel.saveProperties();
        pendantClientJPanel.setPort(55555);
        assertEquals(55555, pendantClientJPanel.getPort());
        pendantClientJPanel.loadProperties();
        assertEquals(p1, pendantClientJPanel.getPort());
    }
    
}
