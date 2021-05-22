PGDMP                         y           ksiegarniatest    13.1    13.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16773    ksiegarniatest    DATABASE     j   CREATE DATABASE ksiegarniatest WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Polish_Poland.1250';
    DROP DATABASE ksiegarniatest;
                postgres    false            �            1259    16788    koszyk    TABLE     �   CREATE TABLE public.koszyk (
    id_koszyka integer NOT NULL,
    id_uzytkownika integer NOT NULL,
    id_ksiazki integer NOT NULL,
    wartosc double precision NOT NULL
);
    DROP TABLE public.koszyk;
       public         heap    postgres    false            �            1259    16803    koszyk_id_koszyka_seq    SEQUENCE     �   ALTER TABLE public.koszyk ALTER COLUMN id_koszyka ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.koszyk_id_koszyka_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    204            �            1259    16781    ksiazki    TABLE       CREATE TABLE public.ksiazki (
    id_ksiazki integer NOT NULL,
    tytul character varying(32) NOT NULL,
    autor character varying(32) NOT NULL,
    cena double precision NOT NULL,
    dostepnosc character varying(32),
    gatunek character varying(32)
);
    DROP TABLE public.ksiazki;
       public         heap    postgres    false            �            1259    16786    ksiazki_id_ksiazki_seq    SEQUENCE     �   ALTER TABLE public.ksiazki ALTER COLUMN id_ksiazki ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ksiazki_id_ksiazki_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    202            �            1259    16774    uzytkownicy    TABLE       CREATE TABLE public.uzytkownicy (
    id_uzytkownika integer NOT NULL,
    imie character varying(20) NOT NULL,
    nazwisko character varying(32) NOT NULL,
    email character varying(48) NOT NULL,
    login character varying(32) NOT NULL,
    haslo character varying(32)
);
    DROP TABLE public.uzytkownicy;
       public         heap    postgres    false            �            1259    16779    uzytkownicy_id_uzytkownika_seq    SEQUENCE     �   ALTER TABLE public.uzytkownicy ALTER COLUMN id_uzytkownika ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.uzytkownicy_id_uzytkownika_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    200            �            1259    24922 
   zamowienie    TABLE     �   CREATE TABLE public.zamowienie (
    id_zamowienia integer NOT NULL,
    adres character varying(255) NOT NULL,
    data_zamowienia date NOT NULL,
    id_koszyka integer NOT NULL,
    stan_realizacji character varying(255) NOT NULL
);
    DROP TABLE public.zamowienie;
       public         heap    postgres    false            �            1259    24920    zamowienie_id_zamowienia_seq    SEQUENCE     �   ALTER TABLE public.zamowienie ALTER COLUMN id_zamowienia ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.zamowienie_id_zamowienia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    207            �          0    16788    koszyk 
   TABLE DATA           Q   COPY public.koszyk (id_koszyka, id_uzytkownika, id_ksiazki, wartosc) FROM stdin;
    public          postgres    false    204   �!       �          0    16781    ksiazki 
   TABLE DATA           V   COPY public.ksiazki (id_ksiazki, tytul, autor, cena, dostepnosc, gatunek) FROM stdin;
    public          postgres    false    202   "       �          0    16774    uzytkownicy 
   TABLE DATA           Z   COPY public.uzytkownicy (id_uzytkownika, imie, nazwisko, email, login, haslo) FROM stdin;
    public          postgres    false    200   �$       �          0    24922 
   zamowienie 
   TABLE DATA           h   COPY public.zamowienie (id_zamowienia, adres, data_zamowienia, id_koszyka, stan_realizacji) FROM stdin;
    public          postgres    false    207   M%       �           0    0    koszyk_id_koszyka_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.koszyk_id_koszyka_seq', 20, true);
          public          postgres    false    205            �           0    0    ksiazki_id_ksiazki_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.ksiazki_id_ksiazki_seq', 27, true);
          public          postgres    false    203            �           0    0    uzytkownicy_id_uzytkownika_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.uzytkownicy_id_uzytkownika_seq', 10, true);
          public          postgres    false    201            �           0    0    zamowienie_id_zamowienia_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.zamowienie_id_zamowienia_seq', 1, false);
          public          postgres    false    206            :           2606    16792    koszyk id_koszyka 
   CONSTRAINT     W   ALTER TABLE ONLY public.koszyk
    ADD CONSTRAINT id_koszyka PRIMARY KEY (id_koszyka);
 ;   ALTER TABLE ONLY public.koszyk DROP CONSTRAINT id_koszyka;
       public            postgres    false    204            8           2606    16785    ksiazki id_ksiazki 
   CONSTRAINT     X   ALTER TABLE ONLY public.ksiazki
    ADD CONSTRAINT id_ksiazki PRIMARY KEY (id_ksiazki);
 <   ALTER TABLE ONLY public.ksiazki DROP CONSTRAINT id_ksiazki;
       public            postgres    false    202            6           2606    16778    uzytkownicy id_uzytkownika 
   CONSTRAINT     d   ALTER TABLE ONLY public.uzytkownicy
    ADD CONSTRAINT id_uzytkownika PRIMARY KEY (id_uzytkownika);
 D   ALTER TABLE ONLY public.uzytkownicy DROP CONSTRAINT id_uzytkownika;
       public            postgres    false    200            <           2606    24929    zamowienie zamowienie_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.zamowienie
    ADD CONSTRAINT zamowienie_pkey PRIMARY KEY (id_zamowienia);
 D   ALTER TABLE ONLY public.zamowienie DROP CONSTRAINT zamowienie_pkey;
       public            postgres    false    207            @           2606    24935 "   koszyk fk93cp5ckuxajk7a26xqbdstv5j    FK CONSTRAINT     �   ALTER TABLE ONLY public.koszyk
    ADD CONSTRAINT fk93cp5ckuxajk7a26xqbdstv5j FOREIGN KEY (id_uzytkownika) REFERENCES public.uzytkownicy(id_uzytkownika);
 L   ALTER TABLE ONLY public.koszyk DROP CONSTRAINT fk93cp5ckuxajk7a26xqbdstv5j;
       public          postgres    false    2870    200    204            ?           2606    24930 "   koszyk fkmot8h35hnglf57j2hub4o5nas    FK CONSTRAINT     �   ALTER TABLE ONLY public.koszyk
    ADD CONSTRAINT fkmot8h35hnglf57j2hub4o5nas FOREIGN KEY (id_ksiazki) REFERENCES public.ksiazki(id_ksiazki);
 L   ALTER TABLE ONLY public.koszyk DROP CONSTRAINT fkmot8h35hnglf57j2hub4o5nas;
       public          postgres    false    204    202    2872            >           2606    16798    koszyk id_ksiazki    FK CONSTRAINT     }   ALTER TABLE ONLY public.koszyk
    ADD CONSTRAINT id_ksiazki FOREIGN KEY (id_ksiazki) REFERENCES public.ksiazki(id_ksiazki);
 ;   ALTER TABLE ONLY public.koszyk DROP CONSTRAINT id_ksiazki;
       public          postgres    false    2872    202    204            =           2606    16793    koszyk id_uzytkownika    FK CONSTRAINT     �   ALTER TABLE ONLY public.koszyk
    ADD CONSTRAINT id_uzytkownika FOREIGN KEY (id_uzytkownika) REFERENCES public.uzytkownicy(id_uzytkownika);
 ?   ALTER TABLE ONLY public.koszyk DROP CONSTRAINT id_uzytkownika;
       public          postgres    false    200    204    2870            �   [   x�M��1��L1(	$�l�u�䤕�g��F+��sⶤ�F��E���*�f���7Ʊ��'�.��,d�e�%�eۇ¾�?�l<�U�      �   �  x��TKN�@]�OQHk��/��0$�(��n�ƞ.�mǲ�lr�1ز��Wj�0y��z�_U�� .�D?�Gp���
�m��
���|ا�}}����Z�_�`64-���H/��ziK�֎����'I�MQ���h4��*S(�J���Z��tv ��8`�&�S����J�I,��Ҿ<�k�%�w�i�@0I��2�ʤ
ou�j2�NW���i�kU](�'���#���%���iڡ�ڏ�/����9�ʪ��K�jV��ǅ��"���65��U:�F7C:�;꥗�y�ʖE���n��̎�/e]Rߔ�@��5H��f��	�i&Ś���"�E�|8��GUݮk��2�Η�e�5��f���5����'�Ĳm��״H�[9��K@�\�g�ǲ)_�V��H��a�ǰ���c5j�Β"t"�p�F2���/��Wy���M��<}�ǵy5��������b��m�~1��3>�E����h���l����_�eW��\�eK���Ob�<쬷���;�봀#�xJ�Uj�m���'{r�-���>�փ�� \7��2�UNx�[�:8�����p���R�#D��m�q^�Lu77�<�t�����b���e\�yM����L��/OGYӒ;b���|uO�f����5�o��P�3��_��� �      �   {   x�=�M
�0����FpW�
z7C���k^L,�{7�e
��0?���o�b3¦���䒂��d�~L:�W�%L���v����;9�ĕ5�����pR7�S�eM>?8G�N���D��%2�      �      x������ � �     