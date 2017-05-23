package body MatrixMult is   
    procedure MatMult(A:in TwoMatrix;B:in TwoMatrix;C:out TwoMatrix) is
        task type T is
            entry Start(Num: in Integer);
        end T;
        task body T is 
            SUM: Integer:=0;
            J: Integer;
        begin 
            accept Start(Num: in Integer) do
                J:= Num;
            end Start;
            for I in 1..SIZE loop
                for K in 1..SIZE loop
                    SUM:= SUM+A(I,K)*B(K,J);
                end loop;
                C(I,J):=SUM;
                SUM:=0;
            end loop;
        end T;    
        
        type Task_Pool is array(Positive range 1..SIZE) of T;
        My_Pool : Task_Pool;
    
        begin
            for num in My_Pool'Range loop
                My_Pool(num).Start(num);
            end loop; 
    end MatMult;
end MatrixMult;