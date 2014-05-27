--------------------------
DESCRIPTION
--------------------------
The tool finds TSD (target site duplication) of retrotransposons, if any.
The tool accepts a FASTA file with a sequence and a RepeatMasker output file.
The tool outputs for every repeat whether it has TSD and, if yes, info about TSD.


-------------------------
USAGE
-------------------------

Usage: ./run.sh -i <seq.fasta> -r <RepeatMasker.out> [options]
    <seq.fasta>             - input sequence file in FASTA format
    <RepeatMasker.out>      - input RepeatMasker output file

Info keys:
    -h, --help, ?           Show this help
    -v, --version           Show version info

Options:
    -o      - output files prefix. Default: filename of input sequence file
    -m      - turn on repeat merging. If this key is given, then repeats
              which look to be parts of a single repeat will be merged together
              Default: turn off
    -mt     - merge threshold. Is a maximum distance between two repeats
              at which they still can be merged. Default: 20
              You do not have to specify the key "-m", if "-mt" is given
    -tl     - minimum length of TSD. Default: 7
    -ted    - maximum edit distance of TSD. Default: 3
    -rl     - minimum length of repeat (smaller repeat will be ignore).
              Default: 1000
    -dor    - maximum distance of TSD outside of the repeat boundary.
              Default: 200
    -dir    - maximum distance of TSD inside of the repeat boundary. Defaut: 0


For more details see manual.
