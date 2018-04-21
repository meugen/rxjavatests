package ua.in.meugen.myapplication.model.actions.items;

public class ItemsRequest {

    public final int id;
    public final long delay;

    public ItemsRequest(final int id, final long delay) {
        this.id = id;
        this.delay = delay;
    }
}
