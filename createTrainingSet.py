import urllib
import urllib2
import cv2
import numpy as np
import os

def store_raw_images():
    neg_images_link = "http://image-net.org/api/text/imagenet.synset.geturls?wnid=n00017222"
    neg_images_urls = urllib2.urlopen(neg_images_link).read().decode()

    if not os.path.exists('neg'):
        os.makedirs('neg')

    pic_num = 1

    for i in neg_images_urls.split('\n'):
        try:
            print(i)
            urllib.urlretrieve(i, "neg/"+str(pic_num)+'.jpg')
            img = cv2.imread("neg/"+str(pic_num)+'.jpg', cv2.IMREAD_GRAYSCALE)
            resized_image = cv2.resize(img, (100,100))
            cv2.imwrite("neg/"+str(pic_num)+'.jpg', resized_image)
            pic_num += 1

        except Exception as e:
            print(str(e))



def find_uglies():
    for file_type in ['neg']:
        for img in os.listdir(file_type):
            for ugly in os.listdir(uglies):
                try:
                    current_image_path = str(file_type)+'/'+str(img)
                    ugly=cv2.imread('uglies/'+str(ugly))
                    question = cv2.imread(current_image_path)
                    if ugly.shap == question.shap and not(np.bitwise_xor(ugly,question).any()):
                        print('damm girl you ugly!')
                        print(current_image_path)
                        os.remove(current_image_path)

                except Exception as e:
                    print(str(e))



def create_pos_n_neg():
    for file_type in ['neg']:
        for img in os.listdir(file_type):
            if file_type == 'neg':
                line = file_type+'/'+img+'\n'
                with open('bg.txt', 'a') as f:
                    f.write(line)

            elif file_type = 'pos':
                line = file_type+'/'+img+' 1 0 0 50 50\n'
                with open(info.dat,'a') as f:
                    f.write(line)




#store_raw_images()
#find_uglies()
#create_pos_n_neg()
