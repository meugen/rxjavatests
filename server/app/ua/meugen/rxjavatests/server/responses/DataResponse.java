package ua.meugen.rxjavatests.server.responses;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataResponse {

    private List<String> data;

    public DataResponse() {}

    public DataResponse(final Random rand, final int count) {
        data = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            data.add(new BigInteger(100, rand).toString(26));
        }
    }

    public List<String> getData() {
        return data;
    }

    public void setData(final List<String> data) {
        this.data = data;
    }
}
