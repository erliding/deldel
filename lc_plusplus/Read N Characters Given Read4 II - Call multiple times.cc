// The API: int read4(char *buf) reads 4 characters at a time from a file.
//
// The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
//
// By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
//
// Note:
// The read function may be called multiple times.
//


// Forward declaration of the read4 API.
int read4(char *buf);

class Solution {
    char buff[4];
    int offset{0};
    int buffSize{0};
public:
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    int read(char *buf, int n) {
        int count{0};
        while (count != n) {
            if (buffSize == 0) {
                offset = 0;
                if((buffSize = read4(buff)) == 0) break;
            }
            buf[count++] = buff[offset++];
            buffSize--;
        }
        return count;
    }
};
