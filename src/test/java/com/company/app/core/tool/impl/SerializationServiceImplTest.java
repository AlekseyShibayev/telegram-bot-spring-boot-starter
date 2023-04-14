package com.company.app.core.tool.impl;

import com.company.app.core.tool.api.JsonSerializationTool;
import com.google.common.collect.ImmutableList;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

class SerializationServiceImplTest {

    private static final String FILE_NAME = "src/test/resources/core/lot_test.json";

    private JsonSerializationTool<TestLot> jsonSerializationTool;

    @BeforeEach
    public void init() {
        jsonSerializationTool = new JsonSerializationToolImpl<>();
    }

    private void cleanFile() throws IOException {
        FileUtils.write(new File(FILE_NAME), "", Charset.defaultCharset());
    }

    private List<TestLot> createLots() {
        return ImmutableList.<TestLot>builder()
                .add(TestLot.builder().id(1L).name("43409221").price("1500").discount("0.19").build())
                .add(TestLot.builder().id(2L).name("15694225").price("5500").discount("0.17").build())
                .build();
    }

    @SneakyThrows
    @Test
    void saveAndLoadTest() {
        cleanFile();
        List<TestLot> list = createLots();

        jsonSerializationTool.save(list, new File(FILE_NAME));
        List<TestLot> load = jsonSerializationTool.load(new File(FILE_NAME), TestLot.class);

        Assertions.assertEquals(2, load.size());
        Assertions.assertEquals(list.get(0).getPrice(), load.get(0).getPrice());
    }


    @SneakyThrows
    @Test
    void nestedObjectsTest() {
        cleanFile();
        List<TestLot> save = createLotsWithNestedFields();
        jsonSerializationTool.save(save, new File(FILE_NAME));
        List<TestLot> load = jsonSerializationTool.load(new File(FILE_NAME), TestLot.class);

        Assertions.assertEquals(save.size(), load.size());
        Assertions.assertEquals(save.get(0).getPrice(), load.get(0).getPrice());
    }

    private List<TestLot> createLotsWithNestedFields() {
         List<ProductProperty> productPropertiesList = ImmutableList.<ProductProperty>builder()
                 .add(ProductProperty.builder().property("Вес").build())
                 .add(ProductProperty.builder().property("Размер").build())
                 .add(ProductProperty.builder().property("Упаковка").build())
                .build();

        ProductDescription productDescription = new ProductDescription(
                "полиуретан, натуральная кожа",
                "Внимание! При выборе размера ориентируйтесь на размерную сетку, загруженную в фотографии. Российский размер не указывается на коробке. На коробке указан европейский размер. Lumberjack - итальянский обувной бренд для активных людей.. Итальянский бренд был разработан, чтобы привнести в городской стиль лучшее из туризма. Сочетание грубого фасона с использованием натуральных материалов тонкой обработки - главная отличительная особенность бренда. С момента своего запуска **Lumberjack** использует высококачественную кожу и уделяет пристальное внимание отделке своей продукции. Очень прочная, устойчивая, идеально подходящая как для города, так и для улицы обувь стала хитом среди любителей природы, мужчин, женщин и детей всех возрастов. Поклонники, которые любят их уникальный дизайн, ценят преимущества бренда- подлинность, качество, долговечность и уважение к окружающей среде.\")\n"
        );

        TestLot testLot = TestLot.builder()
                .id(2L)
                .name("15694225")
                .price("5500")
                .discount("0.17")
                .productDescription(productDescription)
                .productPropertiesList(productPropertiesList)
                .build();

        return ImmutableList.<TestLot>builder()
                .add(testLot)
                .build();
    }
}