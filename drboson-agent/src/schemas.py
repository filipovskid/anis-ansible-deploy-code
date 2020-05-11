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

status_record_schema = """
{
  "type": "record",
  "name": "StatusRecord",
  "fields": [
    { "name": "run_id", "type":  "string" },
    { "name": "status" , "type":  { "name": "RunStatus", "type": "enum", "symbols": ["PENDING", "RUNNING", "COMPLETED", "FAILED"] } }
  ]
}
"""

log_record_schema = """
{
  "type": "record",
  "name": "LogRecord",
  "fields": [
    { "name": "run_id", "type":  "string" },
    { "name": "log" , "type":  "string" }
  ]
}
"""

file_record_schema = """
{
  "type": "record",
  "name": "FileRecord",
  "fields": [
    { "name": "run_id", "type":  "string" },
    { "name": "file_id", "type":  "string" },
    { "name": "file_name", "type": "string" }, 
    { "name": "file_key" , "type":  "string" }
  ]
}
"""
