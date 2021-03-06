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
package crcl.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


/**
 * Utility class for saving or reading text property files.
 * 
 * @author Will Shackleford
 * {@literal <william.shackleford@nist.gov>,<wshackle@gmail.com>}
 */
public class PropertiesUtils {

    private PropertiesUtils() {
    }

//    public static interface RunnableWithThrow {
//
//        public void run() throws Exception;
//    }
//
//    public static class SwingFuture<T> extends XFuture<T> {
//
//        @Override
//        public T get() throws InterruptedException, ExecutionException {
//            if (SwingUtilities.isEventDispatchThread()) {
//                throw new IllegalStateException("One can not get a swing future result on the EventDispatchThread. (getNow can still be used.)");
//            }
//            return super.get();
//        }
//
//        @Override
//        public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//            if (SwingUtilities.isEventDispatchThread()) {
//                throw new IllegalStateException("One can not get a swing future result on the EventDispatchThread. (getNow can still be used.)");
//            }
//            return super.get(timeout, unit);
//        }
//
//    }
//
//    public static SwingFuture<Void> runOnDispatchThreadWithCatch(final RunnableWithThrow r) {
//        SwingFuture<Void> ret = new SwingFuture<>();
//        try {
//            if (javax.swing.SwingUtilities.isEventDispatchThread()) {
//                r.run();
//                ret.complete(null);
//                return ret;
//            } else {
//
//                javax.swing.SwingUtilities.invokeLater(() -> {
//                    try {
//                        r.run();
//                        ret.complete(null);
//                    } catch (Exception ex) {
//                        ret.completeExceptionally(ex);
//                        Logger.getLogger(PropertiesUtils.class.getName()).log(Level.SEVERE, "", ex);
//                    }
//                });
//                return ret;
//            }
//        } catch (Throwable exception) {
//
//            ret.completeExceptionally(exception);
//            Logger.getLogger(PropertiesUtils.class.getName()).log(Level.SEVERE, "", exception);
//            return ret;
//        }
//    }
//
//    public static SwingFuture<Void> runOnDispatchThread(final Runnable r) {
//        SwingFuture<Void> ret = new SwingFuture<>();
//        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
//            r.run();
//            ret.complete(null);
//            return ret;
//        } else {
//            javax.swing.SwingUtilities.invokeLater(() -> {
//                r.run();
//                ret.complete(null);
//            });
//            return ret;
//        }
//    }
//
//    public static <R> SwingFuture<R> supplyOnDispatchThread(final Supplier<R> s) {
//        SwingFuture<R> ret = new SwingFuture<>();
//        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
//            ret.complete(s.get());
//            return ret;
//        } else {
//            javax.swing.SwingUtilities.invokeLater(() -> ret.complete(s.get()));
//            return ret;
//        }
//    }
//
//    private static <R> R unwrap(XFuture<R> f) {
//        try {
//            return f.get();
//        } catch (InterruptedException | ExecutionException ex) {
//            Logger.getLogger(PropertiesUtils.class.getName()).log(Level.SEVERE, "", ex);
//            return null;
//        }
//    }
//
//    public static <R> XFuture<R> composeOnDispatchThread(final Supplier<XFuture<R>> s) {
//        XFuture<XFuture<R>> ret = new SwingFuture<>();
//        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
//            return s.get();
//        } else {
//            javax.swing.SwingUtilities.invokeLater(() -> ret.complete(s.get()));
//            return ret.thenApply(f -> unwrap(f));
//        }
//    }
//
//    public static void autoResizeTableColWidths(JTable table) {
//
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//        int fullsize = 0;
//        Container parent = table.getParent();
//        if (null != parent) {
//            fullsize = Math.max(parent.getPreferredSize().width, parent.getSize().width);
//        }
//        int sumWidths = 0;
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
//            TableColumn col = colModel.getColumn(i);
//            int width = 0;
//
//            TableCellRenderer renderer = col.getHeaderRenderer();
//            if (renderer == null) {
//                renderer = table.getTableHeader().getDefaultRenderer();
//            }
//            Component headerComp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(),
//                    false, false, 0, i);
//            width = Math.max(width, headerComp.getPreferredSize().width);
//            for (int r = 0; r < table.getRowCount(); r++) {
//                renderer = table.getCellRenderer(r, i);
//                Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, i),
//                        false, false, r, i);
//                width = Math.max(width, comp.getPreferredSize().width);
//            }
//            if (i == table.getColumnCount() - 1) {
//                if (width < fullsize - sumWidths) {
//                    width = fullsize - sumWidths;
//                }
//            }
//            col.setPreferredWidth(width + 2);
//            sumWidths += width + 2;
//        }
//    }
//
//    static public void autoResizeTableRowHeights(JTable table) {
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//        for (int rowIndex = 0; rowIndex < table.getRowCount(); rowIndex++) {
//            int height = 0;
//            for (int colIndex = 0; colIndex < table.getColumnCount(); colIndex++) {
//                DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
//                TableColumn col = colModel.getColumn(colIndex);
//                TableCellRenderer renderer = table.getCellRenderer(rowIndex, colIndex);
//                Object value = table.getValueAt(rowIndex, colIndex);
//                Component comp = renderer.getTableCellRendererComponent(table, value,
//                        false, false, rowIndex, colIndex);
//                Dimension compSize = comp.getPreferredSize();
//                int thisCompHeight = compSize.height;
//                height = Math.max(height, thisCompHeight);
//            }
//            if (height > 0) {
//                table.setRowHeight(rowIndex, height);
//            }
//        }
//    }

    /**
     * Save the given properties in a sorted text file.
     * @param file file to save properties in or overwrite
     * @param props properties to save
     * @throws IOException could not save file
     */
    public static void saveProperties(File file, Properties props) throws IOException {
        List<String> names = new ArrayList<>();
        for(Object key : props.keySet()) {
            names.add(key.toString());
        }
        Collections.sort(names);
        StackTraceElement ste[] = Thread.currentThread().getStackTrace();
        try(PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            props.store(pw, "");
        } 
    }
    
}
