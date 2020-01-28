# Huffman-Binary-Tree-Encoder-Decoder

## Output:

greghab@nixGreg> java Huffman                                                                                                                                                                                                                                                   ~/Huffman-Coding-Compression-and-Decompression
Input file is:
"a b e b c d"
Input Frequency Map:{ =[5,  ], a=[1, a], b=[2, b], c=[1, c], d=[1, d], e=[1, e]}
Binary Tree: 
                [1, e]
            [2, null]
                [1, d]
        [4, null]
            [2, b]
    [6, null]
            [1, c]
        [2, null]
            [1, a]
[11, null]
    [5,  ]
Encoding Map: 
{ =0, a=100, b=110, c=101, d=1110, e=1111}
Decoding Map: 
{0= , 100=a, 101=c, 110=b, 1110=d, 1111=e}
fileSize(input.txt) : bytes : 12
fileSize(encoded.txt) : bytes : 3
fileSize(decoded.txt) : bytes : 10
You have compressed the file: 400.0%!
Input file is:
"helloworld."
Input Frequency Map:{.=[1, .], d=[1, d], e=[1, e], h=[1, h], l=[3, l], o=[2, o], r=[1, r], w=[1, w]}
Binary Tree: 
                [1, d]
            [2, null]
                [1, r]
        [4, null]
                [1, w]
            [2, null]
                [1, .]
    [7, null]
        [3, l]
[11, null]
            [1, e]
        [2, null]
            [1, h]
    [4, null]
        [2, o]
Encoding Map: 
{.=1100, d=1111, e=011, h=010, l=10, o=00, r=1110, w=1101}
Decoding Map: 
{00=o, 010=h, 011=e, 10=l, 1100=., 1101=w, 1110=r, 1111=d}
fileSize(input2.txt) : bytes : 12
fileSize(encoded2.txt) : bytes : 4
fileSize(decoded2.txt) : bytes : 11
You have compressed the file: 300.0%!
Input file is:
"A great painter does not content himself by affecting us with his masterpieces; ultimately, he succeeds in changing the landscape of our minds. Once a miniaturist's artistry enters our souls this way, it becomes the criterion for the beauty of our world. - Enishte"
Input Frequency Map:{ =[43,  ], '=[1, '], ,=[2, ,], -=[1, -], .=[2, .], ;=[1, ;], A=[1, A], E=[1, E], O=[1, O], a=[13, a], b=[3, b], c=[10, c], d=[5, d], e=[25, e], f=[6, f], g=[4, g], h=[10, h], i=[19, i], l=[6, l], m=[6, m], n=[15, n], o=[13, o], p=[3, p], r=[14, r], s=[19, s], t=[23, t], u=[9, u], w=[3, w], y=[5, y]}
Binary Tree: 
            [43,  ]
        [86, null]
                [23, t]
            [43, null]
                    [10, c]
                [20, null]
                        [5, d]
                    [10, null]
                            [3, w]
                        [5, null]
                                [1, -]
                            [2, null]
                                [1, ']
    [160, null]
                [19, i]
            [38, null]
                    [10, h]
                [19, null]
                    [9, u]
        [74, null]
                [19, s]
            [36, null]
                        [5, y]
                    [9, null]
                                [1, A]
                            [2, null]
                                [1, ;]
                        [4, null]
                                [1, O]
                            [2, null]
                                [1, E]
                [17, null]
                            [2, ,]
                        [4, null]
                            [2, .]
                    [8, null]
                        [4, g]
[264, null]
                [15, n]
            [29, null]
                [14, r]
        [55, null]
                [13, a]
            [26, null]
                [13, o]
    [104, null]
            [25, e]
        [49, null]
                    [6, m]
                [12, null]
                    [6, l]
            [24, null]
                    [6, f]
                [12, null]
                        [3, p]
                    [6, null]
                        [3, b]
Encoding Map: 
{ =111, '=11000000, ,=1000011, -=11000001, .=1000010, ;=10001010, A=10001011, E=10001000, O=10001001, a=0101, b=000000, c=11001, d=110001, e=001, f=00001, g=100000, h=10101, i=1011, l=00010, m=00011, n=0111, o=0100, p=000001, r=0110, s=1001, t=1101, u=10100, w=1100001, y=100011}
Decoding Map: 
{000000=b, 000001=p, 00001=f, 00010=l, 00011=m, 001=e, 0100=o, 0101=a, 0110=r, 0111=n, 100000=g, 1000010=., 1000011=,, 10001000=E, 10001001=O, 10001010=;, 10001011=A, 100011=y, 1001=s, 10100=u, 10101=h, 1011=i, 11000000=', 11000001=-, 1100001=w, 110001=d, 11001=c, 1101=t, 111= }
fileSize(input3.txt) : bytes : 265
fileSize(encoded3.txt) : bytes : 140
fileSize(decoded3.txt) : bytes : 264
You have compressed the file: 189.0%!
Input file is:
"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
Input Frequency Map:{a=[138, a], b=[44, b]}
Binary Tree: 
    [138, a]
[182, null]
    [44, b]
Encoding Map: 
{a=1, b=0}
Decoding Map: 
{0=b, 1=a}
fileSize(input4.txt) : bytes : 183
fileSize(encoded4.txt) : bytes : 22
fileSize(decoded4.txt) : bytes : 176
You have compressed the file: 832.0%!
