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

import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.MessageType;
import crcl.base.MiddleCommandType;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.checkerframework.checker.nullness.qual.Nullable;

/*
 * 
 * NOTE: Comments beginning with {@literal @} or {@literal >>>} are used by Checker Framework Comments
 * beginning with {@literal @} must have no spaces in the comment or Checker will ignore
 * it.
 *
 * See http://types.cs.washington.edu/checker-framework for null pointer
 * checks. This file can be compiled without the Checker Framework, but using
 * the framework allows potential NullPointerExceptions to be found.
 */

 /*>>>
import org.checkerframework.checker.nullness.qual.*;
 */
/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CRCLCommandWrapper extends MessageType {

    public static interface CRCLCommandWrapperConsumer {

        public void accept(CRCLCommandWrapper wrapper);
    }
    private MiddleCommandType wrappedCommand;

    private @Nullable
    CRCLProgramType curProgram = null;

    private int curProgramIndex = -1;

    public int getCurProgramIndex() {
        return curProgramIndex;
    }

    public void setCurProgramIndex(int curProgramIndex) {
        this.curProgramIndex = curProgramIndex;
    }

    public @Nullable
    CRCLProgramType getCurProgram() {
        return curProgram;
    }

    public void setCurProgram(CRCLProgramType curProgram) {
        this.curProgram = curProgram;
    }

    private CRCLCommandWrapper(MiddleCommandType wrappedCommand) {
        this.wrappedCommand = wrappedCommand;
        this.commandID = wrappedCommand.getCommandID();
        this.name = wrappedCommand.getName();
        if (wrappedCommand instanceof MessageType) {
            this.message = ((MessageType) wrappedCommand).getMessage();
        }
    }

    public static CRCLCommandWrapper wrapWithOnDone(MiddleCommandType wrappedCommand,
            CRCLCommandWrapperConsumer listener) {
        CRCLCommandWrapper cmd = new CRCLCommandWrapper(wrappedCommand);
        cmd.addOnDoneListener(listener);
        return cmd;
    }

    public static CRCLCommandWrapper wrapWithOnStart(MiddleCommandType wrappedCommand,
            CRCLCommandWrapperConsumer listener) {
        CRCLCommandWrapper cmd = new CRCLCommandWrapper(wrappedCommand);
        cmd.addOnStartListener(listener);
        return cmd;
    }

    public static CRCLCommandWrapper wrapWithOnError(MiddleCommandType wrappedCommand,
            CRCLCommandWrapperConsumer listener) {
        CRCLCommandWrapper cmd = new CRCLCommandWrapper(wrappedCommand);
        cmd.addOnErrorListener(listener);
        return cmd;
    }

    public static CRCLCommandWrapper wrap(MiddleCommandType wrappedCommand,
            CRCLCommandWrapperConsumer onStartListener,
            CRCLCommandWrapperConsumer onDoneListener,
            CRCLCommandWrapperConsumer onErrorListener) {
        CRCLCommandWrapper cmd = new CRCLCommandWrapper(wrappedCommand);
        cmd.addOnStartListener(onStartListener);
        cmd.addOnDoneListener(onDoneListener);
        cmd.addOnErrorListener(onErrorListener);
        return cmd;
    }

    public MiddleCommandType getWrappedCommand() {
        return wrappedCommand;
    }

    public void setWrappedCommand(MiddleCommandType wrappedCommand) {
        wrappedCommand.setCommandID(this.commandID);
        this.wrappedCommand = wrappedCommand;
    }

    private static String createAssertErrorString(CRCLCommandType cmd, long id) {
        return "command id being reduced id=" + id + ", cmd=" + CRCLSocket.cmdToString(cmd);
    }

    private static void setCommandId(CRCLCommandType cmd, long id) {
        assert cmd.getCommandID() <= id :
                createAssertErrorString(cmd, id);
        cmd.setCommandID(id);
    }

    @Override
    public void setCommandID(long id) {
        setCommandId(wrappedCommand, id);
        assert this.getCommandID() <= id :
                createAssertErrorString(this, id);
        this.commandID = id;
    }

    @Override
    public long getCommandID() {
        return wrappedCommand.getCommandID();
    }

    @Override
    public void setName(String value) {
        wrappedCommand.setName(value);
        this.name = wrappedCommand.getName();
    }

    @Override
    public String getName() {
        return wrappedCommand.getName();
    }

    private final ConcurrentLinkedDeque<CRCLCommandWrapperConsumer> onStartListeners
            = new ConcurrentLinkedDeque<>();

    public void addOnStartListener(CRCLCommandWrapperConsumer consumer) {
        onStartListeners.add(consumer);
    }

    public void removeOnStartListener(CRCLCommandWrapperConsumer consumer) {
        onStartListeners.add(consumer);
    }

    public void notifyOnStartListeners() {
        for (CRCLCommandWrapperConsumer consumer : onStartListeners) {
            consumer.accept(this);
        }
    }

    private final ConcurrentLinkedDeque<CRCLCommandWrapperConsumer> onDoneListeners
            = new ConcurrentLinkedDeque<>();

    public void addOnDoneListener(CRCLCommandWrapperConsumer consumer) {
        onDoneListeners.add(consumer);
    }

    public void removeOnDoneListener(CRCLCommandWrapperConsumer consumer) {
        onDoneListeners.add(consumer);
    }

    public void notifyOnDoneListeners() {
        for (CRCLCommandWrapperConsumer consumer : onDoneListeners) {
            consumer.accept(this);
        }
    }

    private final ConcurrentLinkedDeque<CRCLCommandWrapperConsumer> onErrorListeners
            = new ConcurrentLinkedDeque<>();

    public void addOnErrorListener(CRCLCommandWrapperConsumer consumer) {
        onErrorListeners.add(consumer);
    }

    public void removeOnErrorListener(CRCLCommandWrapperConsumer consumer) {
        onErrorListeners.add(consumer);
    }

    public void notifyOnErrorListeners() {
        for (CRCLCommandWrapperConsumer consumer : onErrorListeners) {
            consumer.accept(this);
        }
    }

    @Override
    public String toString() {
        return "CrclCommandWrapper{" + "wrappedCommand=" + CRCLSocket.cmdToString(wrappedCommand) + ", curProgram=" + curProgram + ", curProgramIndex=" + curProgramIndex + ", onStartListeners=" + onStartListeners + ", onDoneListeners=" + onDoneListeners + ", onErrorListeners=" + onErrorListeners + '}';
    }

}