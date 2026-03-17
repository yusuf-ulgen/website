from PIL import Image
import sys

def make_transparent(img_path, output_path):
    img = Image.open(img_path)
    img = img.convert("RGBA")
    datas = img.getdata()

    newData = []
    for item in datas:
        # If the pixel is very dark, make it more transparent
        # We can use the brightness as a guide for alpha
        # brightness = (R + G + B) / 3
        brightness = max(item[0], item[1], item[2])
        
        # If it's pure black or very close, make it fully transparent
        if brightness < 10:
            newData.append((item[0], item[1], item[2], 0))
        else:
            # Keep original alpha for others, or set to 255
            newData.append((item[0], item[1], item[2], 255))

    img.putdata(newData)
    img.save(output_path, "PNG")

if __name__ == "__main__":
    make_transparent(sys.argv[1], sys.argv[2])
