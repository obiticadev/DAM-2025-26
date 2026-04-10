package com.masterclass.arrays.nivel08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 08 - Ejercicio 39/40: LIFO y FIFO")
class Ej39_40_StackQueueTest {

    @Test
    void testStack() {
        Ej39_StackSobreArray s = new Ej39_StackSobreArray(5);
        s.push(1);
        s.push(2);
        assertThat(s.pop()).isEqualTo(2);
        assertThat(s.peek()).isEqualTo(1);
        assertThat(s.size()).isEqualTo(1);
    }

    @Test
    void testQueue() {
        Ej40_QueueSobreArray q = new Ej40_QueueSobreArray(3);
        q.enqueue(10);
        q.enqueue(20);
        assertThat(q.dequeue()).isEqualTo(10);
        assertThat(q.size()).isEqualTo(1);
        q.enqueue(30);
        assertThat(q.isFull()).isFalse(); // depende de la lógica circular
    }
}
