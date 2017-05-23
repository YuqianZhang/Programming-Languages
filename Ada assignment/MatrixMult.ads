package MatrixMult is  
    SIZE : integer:= 10;
    type TwoMatrix is array (1..SIZE,1..SIZE) of Integer;
    procedure MatMult(A:in TwoMatrix;B:in TwoMatrix;C:out TwoMatrix);
end MatrixMult;