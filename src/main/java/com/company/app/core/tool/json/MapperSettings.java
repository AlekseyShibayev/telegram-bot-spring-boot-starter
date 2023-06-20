package com.company.app.core.tool.json;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MapperSettings {

    boolean failOnUnknownProperties = true;
}
