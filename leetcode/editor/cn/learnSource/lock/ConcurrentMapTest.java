package leetcode.editor.cn.learnSource.lock;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * @version 1.0
 * @Author zxilong
 * @Date 2024/11/25 下午3:36
 * @注释
 */
public class ConcurrentMapTest {
    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentMap<>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public String get(Object key) {
                return "";
            }

            @Override
            public String put(String key, String value) {
                return "";
            }

            @Override
            public String remove(Object key) {
                return "";
            }

            @Override
            public void putAll(Map<? extends String, ? extends String> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<String> keySet() {
                return Set.of();
            }

            @Override
            public Collection<String> values() {
                return List.of();
            }

            @Override
            public Set<Entry<String, String>> entrySet() {
                return Set.of();
            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String putIfAbsent(String key, String value) {
                return "";
            }

            @Override
            public boolean remove(Object key, Object value) {
                return false;
            }

            @Override
            public boolean replace(String key, String oldValue, String newValue) {
                return false;
            }

            @Override
            public String replace(String key, String value) {
                return "";
            }
        };
    }
}
