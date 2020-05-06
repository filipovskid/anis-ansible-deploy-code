run_record_schema = """
{
  "namespace": "com.filipovski.drboson-agent.serialization.avro",
  "type": "record",
  "name": "RunRecord",
  "fields": [
    { "name": "id", "type":  "string" },
    { "name": "project_id", "type": "string" },
    { "name": "dataset_location" , "type":  "string" }
  ]
}
"""