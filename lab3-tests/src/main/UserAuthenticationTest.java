TEST UserAuthentication
ASSERT_TRUE(authenticate("validUser", "validPass"), "Should succeed with correct credentials")
ASSERT_FALSE(authenticate("validUser", "wrongPass"), "Should fail with wrong credentials")
END TEST
