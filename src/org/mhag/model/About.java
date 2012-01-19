/**
 * @program MHAG
 * @ About Dialog Class
 * @version 1.0
 * @author Tifa@mh3
 *
 */
package org.mhag.model;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.HyperlinkEvent;

/**
 *
 * @author lvmy
 */
public class About extends javax.swing.JDialog {

	/** Creates new form About */
	public About(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollAbout = new javax.swing.JScrollPane();
        jEditorAbout = new javax.swing.JEditorPane();
        labelLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        jScrollAbout.setBorder(javax.swing.BorderFactory.createTitledBorder("About"));

        jEditorAbout.setContentType("text/html");
        jEditorAbout.setEditable(false);
        jEditorAbout.setText("<html>\n  <head>\n  </head>\n  <body>\n    <h2 align = \"center\">\n      MHAG<br />\n     Monster Hunter Armor Generator\n    </h2>\n <p align = \"center\">Desktop 2.0 <br />\n     Release Date: 10/18/2011\n</p>\n <p align = \"left\">MHAG Online: &nbsp;<a href=\"http://www.mhag.info\">www.mhag.info</a><br />\n<align = \"left\">MHAG Desktop Code: &nbsp;<a href=\"http://code.google.com/p/mhag/\">code.google.com/p/mhag</a><br />\n <align = \"left\">MHAG Online Code: &nbsp;<a href=\"http://code.google.com/p/mhag-web/\">code.google.com/p/mhag-web</a>\n</p>\n\n<p align = \"left\">by Tifa@mh3 (Desktop)<br />\n<align = \"left\">Unity Member: &nbsp;<a href=\"http://www.capcom-unity.com/tifa@mh3\">www.capcom-unity.com/tifa@mh3</a><br />\n<align = \"left\">Youtube Channel: &nbsp;<a href=\"http://www.youtube.com/mh3journey\">www.youtube.com/mh3journey</a></p>\n<p align = \"left\">&nbsp; &nbsp; &nbsp; Gondor (Online)<br />\n<align = \"left\">Unity Member: &nbsp;<a href=\"http://www.capcom-unity.com/galileo22\">www.capcom-unity.com/galileo22</a><br />\n  </body>\n\n");
        jEditorAbout.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                jEditorAboutHyperlinkUpdate(evt);
            }
        });
        jScrollAbout.setViewportView(jEditorAbout);

        labelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/mhag/model/pic/logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(labelLogo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollAbout, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLogo)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void jEditorAboutHyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_jEditorAboutHyperlinkUpdate
		try {
			launchBrowser(evt);
		} catch (URISyntaxException ex) {
			Logger.getLogger(MhagGui.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(MhagGui.class.getName()).log(Level.SEVERE, null, ex);
		}
	}//GEN-LAST:event_jEditorAboutHyperlinkUpdate

	private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
		setVisible(false);
	}//GEN-LAST:event_formWindowLostFocus


    public void launchBrowser(HyperlinkEvent evt) throws URISyntaxException, IOException
    {
	    HyperlinkEvent.EventType type = evt.getEventType();
	    if (type == HyperlinkEvent.EventType.ACTIVATED)
	    {
		    StringTokenizer st = new StringTokenizer(evt.getDescription());
		    if(st.hasMoreTokens())
		    {
			    String urlString = st.nextToken();
			    if(Desktop.isDesktopSupported())
			    {
				    URI uri = new URI(urlString);
				    Desktop.getDesktop().browse(uri);
			    }
		    }
	    }
    }

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				About dialog = new About(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {

					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorAbout;
    private javax.swing.JScrollPane jScrollAbout;
    private javax.swing.JLabel labelLogo;
    // End of variables declaration//GEN-END:variables
}
