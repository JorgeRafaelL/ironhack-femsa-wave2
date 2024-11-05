TEST DataProcessing
DATA data = fetchData()
TRY
processData(data)
ASSERT_TRUE(data.processedSuccessfully, "Data should be processed successfully")
CATCH error
ASSERT_EQUALS("Data processing error", error.message, "Should handle processing errors")
END TRY
END TEST
