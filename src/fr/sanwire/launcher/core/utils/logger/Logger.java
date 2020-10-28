package fr.sanwire.launcher.core.utils.logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private final String date = String.format("[%s] ", new SimpleDateFormat("dd/MM/yyyy kk:mm:ss").format(new Date()));

    private String loggerName;
    private File file;

    public Logger(String loggerName){
        this.loggerName = loggerName.endsWith(" ") ? loggerName : loggerName + " ";
        if (this.file.exists()) this.file.delete();
    }

    public Logger(String loggerName, String file){
        this.loggerName = loggerName.endsWith(" ") ? loggerName : loggerName + " ";
        this.file = new File(file);
       if (this.file.exists()) this.file.delete();
    }

    public void log(String message, LogType type){
        String msg;
        if (type.getColor() == null){
            msg = this.date +
                  this.loggerName +
                  "[" + type.getName().toUpperCase() + "] " +
                  message;
            save(msg);
        } else {
            msg = type.getColor().getColor() +
                    this.date +
                    this.loggerName +
                    "[" + type.getName().toUpperCase() + "] " +
                    message +
                    LogColor.RESET.getColor();
            save(msg.substring(5, msg.length() - 4));
        }
        System.out.println(msg);
    }

    private void save(String message){
        if (this.file != null){
            try {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();

                final BufferedReader reader = new BufferedReader(new FileReader(this.file));
                final StringBuilder text = new StringBuilder();

                String line;

                while ((line = reader.readLine()) != null){
                    text.append(line)
                            .append("\n");
                }
                reader.close();

                final String textToWrite = text.toString() + message;
                final BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));

                writer.write(textToWrite);
                writer.flush();
                writer.close();

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
}
