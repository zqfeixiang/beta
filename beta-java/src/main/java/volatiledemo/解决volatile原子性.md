解决volatile不保证原子性问题
1）方法前加synchronized解决

public synchronized void  addPlusPlus() {
  number++;
}

2）加锁解决
// 使用锁保证数据原子性
Lock lock = new ReentrantLock();
public void addPlusPlus() {undefined
   lock.lock();
   number++;
   lock.unlock();
}

3）原子类解决
