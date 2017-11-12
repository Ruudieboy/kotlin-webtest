package nl.avisi.kotlinwebtest.expressions

import org.junit.Assert.assertEquals
import org.junit.Test

class ExpressionsKtTest {
    @Test
    fun findExpressions() {
        val input = "First #{first} second #{SECOND}"
        val actual = findExpressions(input)
        assertEquals(actual.size, 2)
        assertEquals("#{first}", actual[0].first)
        assertEquals(PropertyExpression("first"), actual[0].second)
        assertEquals("#{SECOND}", actual[1].first)
        assertEquals(PropertyExpression("SECOND"), actual[1].second)
    }

    @Test
    fun findExpressions_NoneFound() {
        val input = "First second"
        val actual = findExpressions(input)
        assertEquals(actual.size, 0)
    }

    @Test
    fun findExpressions_UnmatchedBraces() {
        val input = "First #{ second"
        val actual = findExpressions(input)
        assertEquals(actual.size, 0)
    }

    @Test
    fun findExpressions_PropertySyntaxIncorrect() {
        val input = "First #{     }"
        val actual = findExpressions(input)
        assertEquals(actual.size, 0)
    }
}