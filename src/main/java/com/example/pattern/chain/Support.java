package com.example.pattern.chain;

public abstract class Support {
    private final String name;
    private Support next;

    public Support(String name) {
        this.name = name;
    }

    public Support setNext(Support next) {
        this.next = next;
        return next;
    }

    public final void support(Trouble trouble) {
        if (resolve(trouble)) {
            done(trouble);
        } else if (next != null) {
            next.support(trouble);
        } else {
            fail(trouble);
        }
    }

    abstract boolean resolve(Trouble trouble);

    void done(Trouble trouble) {
        System.out.println(trouble + " is resolved by " + this + ".");
    }

    void fail(Trouble trouble) {
        System.out.println(trouble + " cannot be resolved.");
    }

    @Override
    public String toString() {
        return "Support{" + "name='" + name + '\'' + '}';
    }
}
