package util;

import basic.data.structure.MyLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkedListTest {

    @DisplayName("순차적으로 마지막 위치에 삽입한다")
    @Test
    void add() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertThat(list.size()).isEqualTo(3);
    }

    @DisplayName("처음 위치에 요소를 삽입한다")
    @Test
    void addFirst() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.addFirst(0);

        assertThat(list.size()).isEqualTo(4);
        assertThat(list.getFirst()).isEqualTo(0);
        assertThat(list.toArray()).containsExactly(0, 1, 2, 3);
    }

    @DisplayName("마지막 위치에 요소를 삽입한다")
    @Test
    void addLast() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.addLast(4);

        assertThat(list.size()).isEqualTo(4);
        assertThat(list.getLast()).isEqualTo(4);
        assertThat(list.toArray()).containsExactly(1, 2, 3, 4);
    }

    @DisplayName("")
    @Test
    void remove() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(2);

        assertThat(list.size()).isEqualTo(2);
        assertThat(list.getFirst()).isEqualTo(1);
        assertThat(list.getLast()).isEqualTo(3);

        assertThat(list.toArray()).containsExactly(1, 3);
    }

    @DisplayName("")
    @Test
    void removeFirst() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.removeFirst();

        assertThat(list.size()).isEqualTo(2);
        assertThat(list.getFirst()).isEqualTo(2);
        assertThat(list.getLast()).isEqualTo(3);

        assertThat(list.toArray()).containsExactly(2, 3);
    }

    @DisplayName("")
    @Test
    void removeLast() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.removeLast();

        assertThat(list.size()).isEqualTo(2);
        assertThat(list.getFirst()).isEqualTo(1);
        assertThat(list.getLast()).isEqualTo(2);

        assertThat(list.toArray()).containsExactly(1, 2);
    }
}
