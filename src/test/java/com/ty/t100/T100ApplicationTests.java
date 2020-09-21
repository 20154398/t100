package com.ty.t100;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class T100ApplicationTests {

    @Test
    void contextLoads() {
        String name = "wx9f54c36fa32d99d3.o6zAJszphODtndEeG8_88Gqr3_Og.erQqdyNWfQZs03f851e6ef322f80cd516bd9bd0aa2de.mp4";

        String[] fileSplit = name.split("\\.");

        String fileName = new Date().getTime() + "." + fileSplit[fileSplit.length - 1];
    }

}
