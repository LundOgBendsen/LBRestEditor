package com.reprezen.swagedit.tests

import com.reprezen.swagedit.editor.SwaggerDocument
import com.reprezen.swagedit.validation.Validator
import org.junit.Test

import static org.junit.Assert.assertEquals

class ValidationMessageTest {

	val validator = new Validator
	val document = new SwaggerDocument

	def runTest(String expected, String content) {
		document.set(content)
		val errors = validator.validate(document)				
		assertEquals(1, errors.size)

		val error = errors.get(0)
		assertEquals(expected, error.message)
	}
	
	@Test
	def testMessage_additionalItems_notAllowed() {
		var expected = 'instance type (integer) does not match any allowed primitive type (allowed: ["array"])'
		// parameters should contain an array of object
		val content = '''
		swagger: '2.0'
		info:
		  version: 0.0.0
		  title: MyModel
		paths:
		  /p:
		    get:
		      parameters: 2        
		      responses:
		        '200':
		          description: OK
		'''

		runTest(expected, content)
	}

	@Test
	def testMessage_typeNoMatch() {
		var expected = 'instance type (integer) does not match any allowed primitive type (allowed: ["object"])'
		// responses should contain an object
		val content = '''
		swagger: '2.0'
		info:
		  version: 0.0.0
		  title: MyModel
		paths:
		  /p:
		    get:        
		      responses: 2
		'''

		runTest(expected, content)
	}

	@Test
	def	testMessage_notInEnum() {
		val expected = 'instance value ("foo") not found in enum (possible values: ["http","https","ws","wss"])'
		val content = '''
		swagger: '2.0'
		info:
		  version: 0.0.0
		  title: Simple API
		schemes:
		  - http
		  - foo
		paths:
		  /:
		    get:
		      responses:
		        '200':
		          description: OK
		'''

		runTest(expected, content)
	}

	@Test
	def testMessage_oneOf_fail() {
		val expected = 'instance failed to match exactly one schema (matched 0 out of 2)'		
		val content = '''
		swagger: '2.0'
		info:
		  version: 0.0.0
		  title: MyModel
		paths:
		  /p:
		    get:
		      responses:
		        '200':
		          description: 200
		'''
		
		runTest(expected, content)
	}

	@Test
	def testMessage_additionalProperties_notAllowed() {
		val expected = 'object instance has properties which are not allowed by the schema: ["description"]'
		// description should be 2 spaces forward		
		val content = '''
		swagger: '2.0'
		info:
		  version: 0.0.0
		  title: MyModel
		paths:
		  /p:
		    get:
		      responses:
		        '200':
		        description: OK
		'''
		
		runTest(expected, content)
	}

}