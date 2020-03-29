package 队列;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedElement implements Delayed {

    long time = System.currentTimeMillis();
    String message;

    public DelayedElement(String message, long delayTime){
        this.time += delayTime;
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)){
            return 1;
        }else if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)){
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return message + " |执行时间:" + DateFormat.getDateTimeInstance().format(new Date());
    }
}
