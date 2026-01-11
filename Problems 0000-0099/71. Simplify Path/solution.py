class Solution:
    def simplifyPath(self, path: str) -> str:
        stack = []
        dirs = [name for name in path.split("/") if name]
        
        for name in dirs:
            if name == "..":
                if stack:
                    stack.pop()
            elif name != ".":
                stack.append(name)

        return "/" + "/".join(stack)