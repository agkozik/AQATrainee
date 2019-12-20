import java.util.HashSet;
import java.util.Set;

public class Plurality implements Operations {
    //Два множества А и В равны (А=В), если они состоят из одних и тех же элементов
    public boolean equals(Set a, Set b) {
        if (a.size() == b.size() && a.containsAll(b)) {
            return true;
        }
        return false;
    }

    //Объединением (суммой) множеств А и В называется множество А∪В,
    //элементы которого принадлежат хотя бы одному из этих множеств
    public Set<Integer> union(Set a, Set b) {
        Set<Integer> tmp = new HashSet<>(a);
        tmp.addAll(b);
        return tmp;
    }

    //Разностью множеств А и В называется множество АВ, элементы которого
    //принадлежат множеству А, но не принадлежат множеству В
    public Set<Integer> subtract(Set a, Set b) {
        Set<Integer> tmp = new HashSet<>(a);
        tmp.removeAll(b);
        return tmp;
    }

    //Пересечением (произведением) множеств А и В называется множество А ∩ В,
    //элементы которого принадлежат как множеству А, так и множеству В
    public Set<Integer> intersect(Set a, Set b) {
        Set<Integer> tmp = new HashSet<>(a);
        tmp.retainAll(b);
        return tmp;
    }

    //Симметричной разностью множеств А и В называется множество А Δ В,
    //являющееся объединением разностей множеств АВ и ВА, то есть А Δ В = (АВ) ∪(ВА)
    public Set<Integer> symmetricSubtract(Set a, Set b) {
        Set<Integer> c = new HashSet<>(union(a, b));
        Set<Integer> d = new HashSet<>(intersect(a, b));
        c = subtract(c, d);
        return c;
    }
}