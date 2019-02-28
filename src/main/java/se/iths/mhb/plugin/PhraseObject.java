package se.iths.mhb.plugin;

public class PhraseObject {
    private int id;
    private String phrase;
    private String reference;

    public PhraseObject (int id, String phrase, String reference) {
        this.id = id;
        this.phrase = phrase;
        this.reference = reference;
    }


    public int getId() {
        return id;
    }

    public String getPhrase() {
        return phrase;
    }

    public String getReference() {
        return reference;
    }

    @Override
    public String toString() {
        return (
                this.id + ", " +
                this.phrase + ", " +
                this.reference
                );
    }
}
