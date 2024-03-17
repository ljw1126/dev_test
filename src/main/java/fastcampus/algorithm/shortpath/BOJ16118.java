package fastcampus.algorithm.shortpath;

/**
 * 달빛여우(골1)
 * https://www.acmicpc.net/problem/16118
 *
 * 직접 풀이 못함
 * - 일단 여우 속도가 1일때, 늑대가 1/2배, 2배 속도 느려지는거 계산할때 소수점이 나와 버림 ..
 *   - 애초에 거리 비용을 2를 곱하면 되었음
 * - 늑대를 2차원 배열로 풀이하는 것을 생각못함 ([노드][0] : 이전에 2배 속 도착한 경우, [노드][1] : 이전에 1/2 속으로 도착한 경우
 * - 그리고 늑대는 출발할 때 2배 속으로 먼저 달려간다 했으니 dist[start][0] = 0 만 초기화 해야 했음
 *
 * - StringBuilder로 한꺼번에 출력시 1초넘김
 * - 백준 질문게시판 공지사항
 * https://www.acmicpc.net/board/view/23037
 *
 */
public class BOJ16118 {
}
