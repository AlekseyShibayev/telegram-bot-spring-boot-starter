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
}