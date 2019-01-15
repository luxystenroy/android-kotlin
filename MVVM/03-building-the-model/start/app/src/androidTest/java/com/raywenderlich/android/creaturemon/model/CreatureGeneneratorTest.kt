package com.raywenderlich.android.creaturemon.model

import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class CreatureGeneneratorTest {

    private lateinit var creatureGenerator : CreatureGenerator


    @Before
    fun init(){
        creatureGenerator = CreatureGenerator()
    }

    @Test
    fun testGenerateHitPoints(){
        val attributes = CreatureAttributes(7,3,10)
        val name = "Pirachu"
        val expectedCreature = Creature(attributes,84,name)

       assertEquals(expectedCreature,creatureGenerator.generateCreature(attributes,name))

    }
}

