package org.example.designpatterns.behavioral;

// This method allows to execute process sequentially and pass responsibilities
// This can be used to perform a series of checks before logging in eg: authentication
// pass on the responsibilities eg: Log



class Logger {
    enum LogTye{
        INFO,
        DEBUG,
        ERROR,
    }
    Logger nextProcessor;
    Logger(Logger logProcessor){
        nextProcessor = logProcessor;
    }

    void log(LogTye tye, Exception e){
        // if next handler exists pass the responsibility to the next handles
        if(nextProcessor != null){
            nextProcessor.log(tye,e);
        }
    }

}

class InfoLogger extends Logger{
    InfoLogger(Logger logProcessor) {
        super(logProcessor);
    }

    void log(LogTye tye, Exception e){
        if(tye.equals(LogTye.INFO)){
            System.out.println(e.getMessage());
        }
        else if (nextProcessor != null){
            nextProcessor.log(tye,e);
        }
    }
}

class DebugLogger extends Logger{
    DebugLogger(Logger logProcessor) {
        super(logProcessor);
    }

    void log(LogTye tye, Exception e){
        if(tye.equals(LogTye.DEBUG)){
            System.out.println(e.getCause());
        }
        else if (nextProcessor != null){
            nextProcessor.log(tye,e);
        }
    }
}

class ErrorLogger extends Logger{
    ErrorLogger(Logger logProcessor) {
        super(logProcessor);
    }

    void log(LogTye tye, Exception e){
        if(tye.equals(LogTye.ERROR)){
            e.printStackTrace();
        }
        else if (nextProcessor != null){
            nextProcessor.log(tye,e);
        }
    }
}

public class ChainOfResponsibility {
    public static void main(String[] args) {
        Logger logger = new InfoLogger(new DebugLogger( new ErrorLogger(null)));
        Exception e = new Exception("Custom exception");
        logger.log(Logger.LogTye.INFO,e);
        logger.log(Logger.LogTye.DEBUG,e);
        logger.log(Logger.LogTye.ERROR,e);
    }

}
