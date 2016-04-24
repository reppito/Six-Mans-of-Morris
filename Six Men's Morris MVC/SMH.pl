%transiciones
trans(q1, q2).
trans(q1, q7).

trans(q2, q1).
trans(q2, q3).
trans(q2, q5).

trans(q3, q10).
trans(q3, q2).

trans(q4, q5).
trans(q4, q8).

trans(q5, q2).
trans(q5, q4).
trans(q5, q6).

trans(q6, q5).
trans(q6, q9).

trans(q7, q1).
trans(q7, q8).
trans(q7, q14).

trans(q8, q7).
trans(q8, q4).
trans(q8, q11).

trans(q9, q10).
trans(q9, q6).
trans(q9, q13).

trans(q10, q3).
trans(q10, q16).
trans(q10,q9).

trans(q11, q8).
trans(q11, q12).

trans(q12, q11).
trans(q12, q15).
trans(q12, q13).

trans(q13, q12).
trans(q13, q9).

trans(q14, q15).
trans(q14, q7).

trans(q15, q12).
trans(q15, q14).
trans(q15, q16).

trans(q16, q10).
trans(q16, q15).

%posiciones de cada vertice en el arreglo de java
pos(q1,0).
pos(q2,1).
pos(q3,2).
pos(q4,3).
pos(q5,4).
pos(q6,5).
pos(q7,6).
pos(q8,7).
pos(q9,8).
pos(q10,9).
pos(q11,10).
pos(q12,11).
pos(q13,12).
pos(q14,13).
pos(q15,14).
pos(q16,15).

% devuelve todos los movimientos q se puede hacer desde un vertice sin verificar, devuelve las posiciones
%en el arreglo donde se puede mover, y a traves de java se va a esos puntos en especifico
movimientos(X,Y):-trans(X,X1),pos(X1,Y).

%arbol de conocimiento que indica cuando es 3 en linea
tresEnLinea([0,1,2]).
tresEnLinea([0,6,13]).
tresEnLinea([3,4,5]).
tresEnLinea([3,7,10]).
tresEnLinea([13,14,15]).
tresEnLinea([2,9,15]).
tresEnLinea([10,11,12]).
tresEnLinea([5,8,12]).


%funciones sacadas de el libro de ejercicios para verificar cualquier permutuacion
selecciona(X,[X|L],L).
selecciona(X,[Y|L1],[Y|L2]):-selecciona(X,L1,L2).
permutuacion([],[]).
permutuacion(L1,[X|L2]):-selecciona(X,L1,L3),permutuacion(L3,L2).

%verificar si dada una solucion puede o no ser 3 en linea
verificarTresEnLinea(L):-tresEnLinea(X),permutuacion(L,X).

%seleccionar una posicion de una lista entrante

posLista([X|_],I,P,X1):- I=:=P,X1 is X.
posLista([_|L],I,P,X1):- I<P ,posLista(L,I+1,P,X1).
% dada una posicion Y busca los posibles movimientos, luego toma de la
% lista de posiciones el valor de dicho movimiento y define si es o no
% posible el movimiento
verificarMovimientos(X,Y,X1):-movimientos(Y,X2),posLista(X,0,X2,X3),X3=:=0,X1 is X2.

