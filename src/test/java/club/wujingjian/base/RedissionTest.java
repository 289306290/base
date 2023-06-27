package club.wujingjian.base;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedissionTest {
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testLock() throws IOException {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                RLock lock = redissonClient.getLock("myLock");
                try {
                    // 尝试获取锁，最多等待100秒
                    if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                        // TODO: 在锁定期间要执行的代码
                        Thread.sleep(102 );
                        System.out.println("睡着"+Thread.currentThread().getName());
                    } else {
                        System.out.println("获取锁失败"+Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                        lock.unlock();
                        System.out.println("解锁"+Thread.currentThread().getName());
                    }

                }
            }).start();
        }
        System.in.read();

    }
}
