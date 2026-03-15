MOD = 1000000007

class Fancy:
    def __init__(self):
        self.add = 0
        self.mult = 1
        self.seq = []

        # We don't want to add and multiply every value in sequence
        # Let's say we want to just apply changes when we need the value
        # like so:
        # seq[i] * mult + add
        # Then:
        # 1. Add: seq[i] * mult + add + add2 -> just do: add += add2
        # 2. Multiply: (seq[i] * mult + add) * m -> mult *= m AND add *= m
        # 3. Append: We want to find x such that:
        # x * mult + add = original_val
        # x = (original_val - add) / mult = (original_val - add) * mult^-1
        # Once we store this x % mod, we need to make sure that we can
        # restore it to original_val % mod if asked later
        # ((original_val - add) * mult^-1) % mod
        # If we apply operations:
        # ((original_val - add)%mod * (mult^-1)%mod * mult%mod) + add
        # -> need (mult^-1)%mod * mult%mod to cancel out to 1 so we
        # can get the original_val
        # So we need to also store mod inverse val
        # Note: Let's say mult = 3, we can't just do 1/3 because:
        # (1/3) % mod = (0.3333) % mod = 0 % mod
        # 3 % mod * 0 % mod = 0 -> this is not what we want
        # which is why we need mod inverse. Let's say mod = 7, then
        # mod inverse of 3 is 5 since:
        # 3 * 5 % 7 = 15 % 7 = 1

    def append(self, val: int) -> None:
        adjusted = ((MOD + val - self.add) * pow(self.mult, -1, MOD)) % MOD
        self.seq.append(adjusted)

    def addAll(self, inc: int) -> None:
        self.add = (self.add + inc) % MOD

    def multAll(self, m: int) -> None:
        self.add = (self.add * m) % MOD
        self.mult = (self.mult * m) % MOD

    def getIndex(self, idx: int) -> int:
        if idx >= len(self.seq):
            return -1
        return (self.seq[idx] * self.mult + self.add) % MOD


# Your Fancy object will be instantiated and called as such:
# obj = Fancy()
# obj.append(val)
# obj.addAll(inc)
# obj.multAll(m)
# param_4 = obj.getIndex(idx)