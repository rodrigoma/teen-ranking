package br.com.ieab.api;

import lombok.Getter;
import lombok.Setter;

import static br.com.ieab.Commons.hashToName;
import static br.com.ieab.Commons.nameToHash;
import static java.lang.Long.compare;

/**
 * Created by montanha on 29/10/17.
 */
public class Teen implements Comparable<Teen> {

    @Getter
    private String name;

    @Getter
    @Setter
    private long points = 0;

    @Getter
    private String hash;

    public Teen() {
    }

    public Teen(String name, long points) {
        this.name = name;
        this.hash = nameToHash(name);
        this.points = points;
    }

    public Teen(long points, String hash) {
        this.name = hashToName(hash);
        this.hash = hash;
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
        this.hash = nameToHash(name);
    }

    public void setHash(String hash) {
        this.hash = hash;
        this.name = hashToName(hash);
    }

    public int compareTo(Teen compareTeen) {
        return compare(compareTeen.getPoints(), this.points);
    }
}