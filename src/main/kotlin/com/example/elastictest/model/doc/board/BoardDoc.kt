package com.example.elastictest.model.doc.board

import jakarta.persistence.Id
import jakarta.validation.constraints.NotEmpty
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import org.springframework.data.elasticsearch.annotations.Setting
import java.time.ZonedDateTime

@Document(indexName = "board_index")
@Setting(settingPath = "elastic/es-setting.json")
class BoardDoc(title: String, content: String, created: ZonedDateTime) {

    @Id
    var id:Long? = null

    @NotEmpty
    @Field(type = FieldType.Text, analyzer = "kor_nori_analyzer")
    var title: String = title

    @NotEmpty
    @Field(type = FieldType.Text, analyzer = "kor_nori_analyzer")
    var content: String = content

    @Field(type = FieldType.Date)
    var created: ZonedDateTime? = created
}