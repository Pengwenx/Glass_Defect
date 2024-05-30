import cv2
from ultralytics import YOLO

def main():
    # 读取检测模型
    model = YOLO('runs/detect/train2/weights/best.pt')

    # 对图像进行目标检测
    img_path = 'E:/items/test.jpg'
    img = cv2.imread(img_path)
    results = model(img)

    # 对检测结果进行可视化
    annotated_image = results[0].plot()

    # 保存结果
    output_path = 'E:/items/annotated_image.jpg'
    cv2.imwrite(output_path, annotated_image)
    print('success saved')

if __name__ == '__main__':
    main()