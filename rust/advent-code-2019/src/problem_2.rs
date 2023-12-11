fn add_mul_opcode(inp: &Vec<i32>, curr_idx: usize) -> (i32, usize) {
    let index_first = *inp.get(curr_idx + 1).unwrap();
    let index_second = *inp.get(curr_idx + 2).unwrap();
    let resultant_idx = *inp.get(curr_idx + 3).unwrap() as usize;
    if *inp.get(curr_idx).unwrap() == 1 {
        (index_first + index_second, resultant_idx) 
    } else {
        (index_first * index_second, resultant_idx)
    }
}

pub fn solve() -> Vec<i32> {
    let mut inp:Vec<i32> = vec![
        1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,10,1,19,1,19,9,23,1,23,6,27,2,27,
        13,31,1,10,31,35,1,10,35,39,2,39,6,43,1,43,5,47,2,10,47,51,1,5,51,55,
        1,55,13,59,1,59,9,63,2,9,63,67,1,6,67,71,1,71,13,75,1,75,10,79,1,5,
        79,83,1,10,83,87,1,5,87,91,1,91,9,95,2,13,95,99,1,5,99,103,2,103,9,
        107,1,5,107,111,2,111,9,115,1,115,6,119,2,13,119,123,1,123,5,127,1,
        127,9,131,1,131,10,135,1,13,135,139,2,9,139,143,1,5,143,147,1,13,147,
        151,1,151,2,155,1,10,155,0,99,2,14,0,0
    ];
    let mut opcode_index:usize = 0;
    while *inp.get(opcode_index).unwrap() != 99 {
        let code = *inp.get(opcode_index).unwrap();
        opcode_index =  match code {
            1 | 2 => {
                let (result, resultant_idx) = add_mul_opcode(&inp, opcode_index);
                inp[resultant_idx] = result;
                opcode_index + 4
            },
            99 => break,
            _ => panic!("Unknown code encountered!"),
        }
    }
    inp    
}
